package solr;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;

public class SolrQueryTest {
	@Test
	public void queryTest() {
		try {
	        QueryResponse respone = SolrUtil.query("数据");
	        
	        List<Article> articleList = respone.getBeans(Article.class);
	        System.out.println(articleList);
	    } catch (SolrServerException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }	    
	}
	
	@Test
	public void queryHighlightTest() {
	    try {
			SolrUtil.queryHighlight("测试");
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
}
