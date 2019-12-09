package com.goertek.commonlib.provider.manager;

import com.goertek.commonlib.provider.model.Container;
import com.goertek.commonlib.provider.model.Element;
import com.goertek.commonlib.provider.model.Layer;
import com.goertek.commonlib.provider.model.Option;
import com.goertek.commonlib.provider.model.Provider;
import com.goertek.commonlib.provider.model.Providers;
import com.goertek.commonlib.provider.model.Styles;
import com.goertek.commonlib.utils.ContextUtil;
import com.goertek.commonlib.utils.LogUtil;
import com.goertek.commonlib.utils.WatchFaceUtil;

import java.util.Collections;
import java.util.List;

public class ElementProvider {
    private static final String TAG = ElementProvider.class.getSimpleName();
    private Provider mProvider;
    private Providers mProviders;
    private ResourceResolver mResolver;

    private int mDisplayMetrics;

    public ElementProvider(ResourceResolver resolver) {
        mResolver = resolver;
        mProviders = mResolver.parserConfigFile();
        mDisplayMetrics = WatchFaceUtil.getDisplayMetrics(ContextUtil.getContext());
        initProvider();
    }

    private void initProvider() {
        if (mProviders != null) {
            List<Provider> providerList = mProviders.getProviders();
            if (providerList != null && providerList.size() > 0) {
                for (Provider provider : providerList) {
                    if (provider != null) {
                        String dpi = provider.getDpi();
                        if (Integer.parseInt(dpi) == mDisplayMetrics) {
                            mProvider = provider;
                            break;
                        }
                    }
                }
            }
        }
    }

    public List<Element> getElements() {
        if (mProvider == null) {
            return Collections.EMPTY_LIST;
        }

        List<Element> elements = mProvider.getElements();
        if (elements == null || elements.size() <= 0) {
            return Collections.EMPTY_LIST;
        }
        return elements;
    }

    public List<Option> getOptions(String elementLabel) {
        return Collections.EMPTY_LIST;
    }

    public List<Layer> getLayers(String label) {
        return Collections.EMPTY_LIST;
    }

    public List<Container> getContainer() {
        return Collections.EMPTY_LIST;
    }

    public Option getSelectedOption(String label) {
        return new Option();
    }

    public Element getElementWithLabel(String label) {
        if (mProvider == null) {
            return null;
        }

        List<Element> elements = mProvider.getElements();
        LogUtil.i("zyazya","getElementWithLabel:" + elements + ",label:" + label);
        if (elements == null || elements.size() <= 0) {
            return null;
        }

        for (Element element : elements) {
            if (element.getLabel().equals(label)) {
                return element;
            }
        }
        return null;
    }

    public List<Container> getSelectedContainer() {
        return Collections.EMPTY_LIST;
    }

    public Styles getStyles() {
        if (mProvider == null) {
            return null;
        }
        return mProvider.getStyles();
    }

    public boolean saveProviders() {
        return mResolver.writeBackConfigFile(mProviders);
    }
}
