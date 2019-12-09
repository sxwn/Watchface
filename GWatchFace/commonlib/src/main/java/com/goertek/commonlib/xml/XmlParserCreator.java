package com.goertek.commonlib.xml;

import org.xmlpull.v1.XmlPullParser;

/**
 *  接口
 * <p>
 *  @author: carey.yu
 *  @version: 1.0.0
 *  @since: 2019/7/12
 */
public interface XmlParserCreator {
    XmlPullParser createParser();
}
