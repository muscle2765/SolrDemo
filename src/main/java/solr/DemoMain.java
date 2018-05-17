package solr;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;

public class DemoMain {

	public static void main(String[] args) {
		Article article = new Article();
	    article.setId(UUID.randomUUID().toString());
	    article.setName("数据4");
	    article.setContent("数据4");
	    article.setCreateTime(new Date());
	    SolrUtil.saveSolrResource(article);
	    try {
	        QueryResponse respone = SolrUtil.query("数据");
	        
	        List<Article> articleList = respone.getBeans(Article.class);
	        System.out.println(articleList);
	    } catch (SolrServerException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    try {
			SolrUtil.queryHighlight("药店");
		} catch (SolrServerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
