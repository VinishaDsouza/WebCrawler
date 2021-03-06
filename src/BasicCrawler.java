/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.IOException;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.http.Header;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

//import resources.CrawlerGraph;
//import resources.Vertex;


public class BasicCrawler extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");
    private static final Pattern IMAGE_ = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        // Ignore the url if it has an extension that matches our defined set of image extensions.
//        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
//            return false;
//        }
       return true;

        // Only accept the url if it is in the "www.ics.uci.edu" domain and protocol is "http".
        //return href.startsWith("http://sikaman.dyndns.org:8888/courses/4601/resources/N-0.html");
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page) {
        int docid = page.getWebURL().getDocid();
        String url = page.getWebURL().getURL();
        String domain = page.getWebURL().getDomain();
        String path = page.getWebURL().getPath();
        String subDomain = page.getWebURL().getSubDomain();
        String parentUrl = page.getWebURL().getParentUrl();
        String anchor = page.getWebURL().getAnchor();
        
        try {
			DatabaseManager.getInstance().open(docid, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        System.out.println("Docid: {}" + docid);
        System.out.println("URL: {}"+ url);
        System.out.println("Domain: '{}'" + domain);
        System.out.println("Sub-domain: '{}'" + subDomain);
        System.out.println("Path: '{}'"+  path);
        System.out.println("Parent page: {}" + parentUrl);
        System.out.println("Anchor text: {}" + anchor);

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String text = htmlParseData.getText();
            String html = htmlParseData.getHtml();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            System.out.println("Text length: {}" + text.length());
            System.out.println("Html length: {}" + html.length());
            System.out.println("Number of outgoing links: {}" + links.size());
        }

        Header[] responseHeaders = page.getFetchResponseHeaders();
        if (responseHeaders != null) {
            System.out.println("Response headers:");
            for (Header header : responseHeaders) {
                System.out.println("\t{}: {}" + header.getName() + header.getValue());
            }
        }

        System.out.println("=============");
    }
}