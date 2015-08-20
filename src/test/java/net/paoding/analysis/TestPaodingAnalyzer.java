package net.paoding.analysis;

/*
 *   Copyright (c) 2015 Lemur Consulting Ltd.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import java.io.IOException;

import net.paoding.analysis.analyzer.PaodingAnalyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.junit.Test;

public class TestPaodingAnalyzer extends BaseTokenStreamTestCase {

    @Test
    public void testBasics() throws IOException {

        PaodingAnalyzer analyzer = new PaodingAnalyzer();
        assertTokenStreamContents(analyzer.tokenStream("field", "汉文化和服装 汉文化"),
                new String[]{
                      "汉文", "文化", "和服", "服装", "汉文", "文化"
                });

    }

}
