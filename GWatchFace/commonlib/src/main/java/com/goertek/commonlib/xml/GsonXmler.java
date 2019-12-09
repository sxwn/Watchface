package com.goertek.commonlib.xml;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * 上层解析类
 * <p>
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/12
 */
public class GsonXmler {

    private Gson mCore;
    private XmlReader.Options mOptions;

    public GsonXmler(Gson gson, XmlReader.Options options) {
        mCore = gson;
        mOptions = options;
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonIOException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    public <T> T fromXml(String str, Class<T> cls) throws JsonSyntaxException {
        return Primitives.wrap(cls).cast(fromXml(str, cls instanceof Type ? cls : null));
    }

    public <T> T fromXml(String str, Type type) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return fromXml(new StringReader(str), type);
    }

    public <T> T fromXml(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        XmlReader xmlReader = new XmlReader(reader, mOptions);
        T fromXml = fromXml(xmlReader, cls);
        assertFullConsumption(fromXml, xmlReader);
        return Primitives.wrap(cls).cast(fromXml);
    }

    public <T> T fromXml(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        XmlReader xmlReader = new XmlReader(reader, mOptions);
        T fromXml = fromXml(xmlReader, type);
        assertFullConsumption(fromXml, xmlReader);
        return fromXml;
    }

    private <T> T fromXml(XmlReader xmlReader, Type type) throws JsonIOException, JsonSyntaxException {
        return mCore.fromJson(xmlReader, type);
    }

    public void toXml(Writer writer, Object obj) throws JsonIOException, JsonSyntaxException, IOException {
        try {
            mCore.toJson(obj, obj.getClass(), new XmlWriter(writer, obj.getClass().getSimpleName()));
        } catch (JsonIOException e2) {
            throw new JsonIOException(e2);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public String toString() {
        return mCore.toString();
    }
}
