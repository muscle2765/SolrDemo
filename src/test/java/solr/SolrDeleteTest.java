package solr;
import org.junit.Test;

public class SolrDeleteTest {
	@Test
	public void deleteTest() {
		SolrUtil.removeAllData();
	}
}
