package com.goertek.commonlib.xml;

import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;

import com.google.gson.stream.JsonWriter;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class XmlWriter extends JsonWriter {
    private static final String TAG = "XmlWriter";

    private static final int ATTR_SIZE = 20;
    private static final int CHILD_SIZE = 10;
    private static final int MEMBER_SIZE = 20;

    private static final int WRITE_DOCUMENT = 1;
    private static final int WRITE_OBJECT = 2;
    private static final int WRITE_ARRAY = 3;

    private String mCurrentName;
    private String mRootName;
    private XmlSerializer mSerializer;
    private Stack<XmlNode> mNodeStack = new Stack<>();
    private Stack<Integer> mStatueStack = new Stack<>();

    private class XmlNode {
        private static final String SPACE = "    ";
        private String arrayName;
        private String nodeName;
        private List<Pair<String, String>> attrList = new ArrayList<>(ATTR_SIZE);
        private List<Pair<String, String>> memberList = new ArrayList<>(MEMBER_SIZE);
        private List<XmlNode> objList = new ArrayList<>(CHILD_SIZE);

        XmlNode(String str) {
            nodeName = str;
        }

        public void addAttr(String key, String value) {
            attrList.add(new Pair<>(key, value));
        }

        public void addMember(String key, String value) {
            memberList.add(new Pair<>(key, value));
        }

        public void addNode(XmlNode xmlNode) {
            objList.add(xmlNode);
        }

        public void setArrayName(String name) {
            arrayName = name;
        }

        public void write(XmlSerializer xmlSerializer, String str) throws IOException {
            xmlSerializer.text(System.lineSeparator());
            xmlSerializer.text(str);
            xmlSerializer.startTag(null, nodeName);
            for (Pair attr : attrList) {
                mSerializer.attribute(null, (String) attr.first, (String) attr.second);
            }
            for (Pair name : memberList) {
                xmlSerializer.text(System.lineSeparator());
                xmlSerializer.text(str + SPACE);
                xmlSerializer.startTag(null, (String) name.first);
                xmlSerializer.text((String) name.second);
                xmlSerializer.endTag(null, (String) name.first);
            }
            for (XmlNode write : objList) {
                write.write(xmlSerializer, str + SPACE);
            }
            xmlSerializer.text(System.lineSeparator());
            xmlSerializer.text(str);
            xmlSerializer.endTag(null, nodeName);
        }
    }

    XmlWriter(Writer writer, String str) throws IOException {
        super(writer);
        mStatueStack.push(WRITE_DOCUMENT);
        mSerializer = Xml.newSerializer();
        mSerializer.setOutput(writer);
        mRootName = str;
    }

    public JsonWriter beginObject() throws IOException {
        int intValue = mStatueStack.peek();
        switch (intValue) {
            case WRITE_DOCUMENT:
                mSerializer.startDocument("utf-8", true);
                mStatueStack.pop();
                mStatueStack.push(WRITE_OBJECT);
                mNodeStack.push(new XmlNode(mRootName));
                break;
            case WRITE_OBJECT:
                if (!mNodeStack.isEmpty()) {
                    XmlNode xmlNode = new XmlNode(mCurrentName);
                    mNodeStack.peek().addNode(xmlNode);
                    mNodeStack.push(xmlNode);
                }
                mStatueStack.push(WRITE_OBJECT);
                break;
            case WRITE_ARRAY:
                if (!mNodeStack.isEmpty()) {
                    XmlNode peek = mNodeStack.peek();
                    XmlNode xmlNodeArray = new XmlNode(peek.arrayName);
                    peek.addNode(xmlNodeArray);
                    mNodeStack.push(xmlNodeArray);
                }
                mStatueStack.push(WRITE_OBJECT);
                break;
        }
        return this;
    }

    public JsonWriter endObject() throws IOException {
        XmlNode xmlNode = null;
        int value = mStatueStack.peek();
        if (value == WRITE_OBJECT) {
            if (!mNodeStack.isEmpty()) {
                xmlNode = mNodeStack.peek();
                mNodeStack.pop();
            }
            mStatueStack.pop();
        }
        if (mStatueStack.isEmpty() && mNodeStack.isEmpty()) {
            if (xmlNode != null) {
                xmlNode.write(mSerializer, "");
            }
            mSerializer.endDocument();
            mSerializer.text(System.lineSeparator());
            mSerializer.flush();
        }
        return this;
    }

    public JsonWriter beginArray() {
        mStatueStack.push(WRITE_ARRAY);
        if (!mNodeStack.isEmpty()) {
            mNodeStack.peek().setArrayName(mCurrentName);
        }
        return this;
    }

    public JsonWriter endArray() {
        mStatueStack.pop();
        return this;
    }

    public JsonWriter name(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        mCurrentName = str;
        return this;
    }

    public JsonWriter value(String str) {
        if (str == null) {
            return nullValue();
        }
        saveXmlNode(str);
        return this;
    }

    public JsonWriter jsonValue(String str) throws IOException {
        mSerializer.text(str);
        mSerializer.flush();
        return this;
    }

    public JsonWriter nullValue() {
        saveXmlNode(null);
        return this;
    }

    private void saveXmlNode(String str) {
        int value = mStatueStack.peek();
        if (value == WRITE_OBJECT && !TextUtils.isEmpty(mCurrentName) && !mNodeStack.isEmpty()) {
            XmlNode peek = mNodeStack.peek();
            if (!TextUtils.isEmpty(mCurrentName) && !TextUtils.isEmpty(str)) {
                if (mCurrentName.startsWith("@")) {
                    peek.addAttr(mCurrentName.substring(1), str);
                } else {
                    peek.addMember(mCurrentName, str);
                }
            }
        }
    }

    public JsonWriter value(boolean value) {
        saveXmlNode(value ? "true" : "false");
        return this;
    }

    public JsonWriter value(Boolean bool) {
        if (bool == null) {
            return nullValue();
        }
        saveXmlNode(bool ? "true" : "false");
        return this;
    }

    public JsonWriter value(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
        }
        saveXmlNode(Double.toString(value));
        return this;
    }

    public JsonWriter value(long value) {
        saveXmlNode(Long.toString(value));
        return this;
    }

    public JsonWriter value(Number number) {
        if (number == null) {
            return nullValue();
        }
        String obj = number.toString();
        if (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN")) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        saveXmlNode(obj);
        return this;
    }

}
