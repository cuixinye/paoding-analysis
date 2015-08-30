package net.paoding.analysis.analyzer;

import java.io.Reader;

import net.paoding.analysis.analyzer.impl.MaxWordLengthTokenCollector;
import net.paoding.analysis.analyzer.impl.MostWordsTokenCollector;
import net.paoding.analysis.knife.Knife;
import net.paoding.analysis.knife.PaodingMaker;
import org.apache.lucene.analysis.Analyzer;


/**
 *
 *
 * @author ZhenQin, linliangyi
 */
public class PaodingAnalyzer extends Analyzer {

	// -------------------------------------------------

	/**
	 * 最多切分
	 */
	public static final int MOST_WORDS_MODE = 1;

	/**
	 * 按最大切分
	 */
	public static final int MAX_WORD_LENGTH_MODE = 2;

	// -------------------------------------------------
	/**
	 * 用于向PaodingTokenizer提供，分解文本字符
	 * 
	 * @see net.paoding.analysis.analyzer.PaodingTokenizer#incrementToken()
	 * 
	 */
	private final Knife knife;

	/**
	 * @see #MOST_WORDS_MODE
	 * @see #MAX_WORD_LENGTH_MODE
	 */
	private final int mode;

    public PaodingAnalyzer() {
        this(PaodingMaker.make(PaodingMaker.DEFAULT_PROPERTIES_PATH));
    }

	/**
	 * @param knife
	 */
	public PaodingAnalyzer(Knife knife) {
		this(knife, MOST_WORDS_MODE);
	}

	/**
	 * @param knife
	 * @param mode
	 */
	public PaodingAnalyzer(Knife knife, int mode) {
		this.knife = knife;
		this.mode = mode;
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
		if (knife == null) {
			throw new NullPointerException("knife should be set before token");
		}
		// PaodingTokenizer是TokenStream实现，使用knife解析reader流入的文本
		return new TokenStreamComponents(new PaodingTokenizer(reader, 
				knife, createTokenCollector()));
	}



    protected TokenCollector createTokenCollector() {
        switch (mode) {
            case MOST_WORDS_MODE:
                return new MostWordsTokenCollector();
            case MAX_WORD_LENGTH_MODE:
                return new MaxWordLengthTokenCollector();
            default:
                throw new Error("never happened");
        }
    }


    // -------------------------------------------------

    public Knife getKnife() {
        return knife;
    }

    public int getMode() {
        return mode;
    }

}
