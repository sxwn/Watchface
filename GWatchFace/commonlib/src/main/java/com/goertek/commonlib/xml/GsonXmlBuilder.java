package com.goertek.commonlib.xml;

import com.google.gson.GsonBuilder;

/**
 *
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/12
 */
public class GsonXmlBuilder {

    private GsonBuilder mCoreBuilder;
    private final XmlReader.Options mOptions = new XmlReader.Options();

    public GsonXmlBuilder() {
        XmlReader.Options options = mOptions;
        options.skipRoot = true;
        options.namespaces = false;
        options.sameNameList = true;
    }

    public GsonXmlBuilder setSkipRoot(boolean z) {
        mOptions.skipRoot = z;
        return this;
    }

    public GsonXmlBuilder setTreatNamespaces(boolean z) {
        mOptions.namespaces = z;
        return this;
    }

    public GsonXmlBuilder setSameNameLists(boolean z) {
        mOptions.sameNameList = z;
        return this;
    }

    public GsonXmlBuilder setPrimitiveArrays(boolean z) {
        mOptions.primitiveArrays = z;
        return this;
    }

    public GsonXmlBuilder setRootArrayPrimitive(boolean z) {
        mOptions.rootArrayPrimitive = z;
        return this;
    }

    public GsonXmler create() {
        if (mCoreBuilder == null) {
            mCoreBuilder = new GsonBuilder();
        }
        return new GsonXmler(mCoreBuilder.create(), mOptions);
    }
}
