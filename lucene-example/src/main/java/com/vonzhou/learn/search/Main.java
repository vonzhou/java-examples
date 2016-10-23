package com.vonzhou.learn.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.AttributeFactory;
import org.apache.lucene.util.Version;

import java.io.StringReader;
import java.util.List;

/**
 * @version 2016/10/20.
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        String text = "Lucene is a simple yet powerful java based search library. 1345@qq.com ";
//        Analyzer analyzer = new StandardAnalyzer();
//        List ss = TokenizerWithAnalyzer.tokenizeString(analyzer, text);
//        System.out.print("==>" + ss + " \n");


        AttributeFactory factory = AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY;
        Tokenizer tokenizer = new StandardTokenizer(factory);
        tokenizer.setReader(new StringReader(" asdf,asdfsaf, abc@qq.com lasdf"));
        tokenizer.reset();
        while (tokenizer.incrementToken()) {
            System.out.println(tokenizer.getAttribute(CharTermAttribute.class));
        }
    }
}
