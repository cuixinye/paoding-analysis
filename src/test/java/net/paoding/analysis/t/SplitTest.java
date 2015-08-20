package net.paoding.analysis.t;

import java.io.StringReader;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import net.paoding.analysis.analyzer.PaodingTokenizer;
import net.paoding.analysis.analyzer.impl.MaxWordLengthTokenCollector;
import net.paoding.analysis.knife.Paoding;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.junit.Test;

/**
 * <pre>
 *
 * Created by IntelliJ IDEA.
 * User: ZhenQin
 * Date: 14-1-7
 * Time: 下午3:42
 * To change this template use File | Settings | File Templates.
 *
 * </pre>
 *
 * @author ZhenQin
 */
public class SplitTest {

    Analyzer ANALYZER = new PaodingAnalyzer();

    public SplitTest() {
    }


    @Test
    public void testSplitChinese() throws Exception {
        String txt = "汉文化和服装 汉文化";
        PaodingTokenizer tokenizer = new PaodingTokenizer(
                new StringReader(txt),
                new Paoding(),
                new MaxWordLengthTokenCollector());

        PaodingAnalyzer analyzer = new PaodingAnalyzer();
        TokenStream ts = analyzer.tokenStream("field", txt);

        CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);

        ts.reset();
        while (ts.incrementToken()) {
            System.out.println(termAtt.toString());
        }
    }


    @Test
    public void testParse() throws Exception {
        Query query = new QueryParser(Version.LUCENE_46,
                "title", ANALYZER).parse("title:你吃饭被撑死了吗");

        System.out.println(query);
    }
}
