package solr;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class SolrAddTest {
	@Test
	public void addTest() {
		Article article = new Article();
		article.setId(UUID.randomUUID().toString());
		article.setName("数据4");
		article.setContent("数据4");
		article.setCreateTime(new Date());
		SolrUtil.saveSolrResource(article);
		
		article = new Article();
		article.setId(UUID.randomUUID().toString());
		article.setName("吉林市长春药店");
		article.setContent("吉林市长春药店");
		article.setCreateTime(new Date());
		SolrUtil.saveSolrResource(article);
		
		article = new Article();
		article.setId(UUID.randomUUID().toString());
		article.setName("测试数据1");
		article.setContent("吉林市长春药店");
		article.setCreateTime(new Date());
		SolrUtil.saveSolrResource(article);
		
		article = new Article();
		article.setId(UUID.randomUUID().toString());
		article.setName("测试数据2");
		article.setContent("testContent");
		article.setCreateTime(new Date());
		SolrUtil.saveSolrResource(article);
	}
}
