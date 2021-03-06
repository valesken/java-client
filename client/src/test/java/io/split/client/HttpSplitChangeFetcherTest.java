package io.split.client;

import io.split.engine.metrics.Metrics;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpSplitChangeFetcherTest {

    @Test
    public void testDefaultURL() throws URISyntaxException {
        URI rootTarget = URI.create("https://api.split.io");
        CloseableHttpClient httpClient = HttpClients.custom().build();
        Metrics.NoopMetrics metrics = new Metrics.NoopMetrics();
        HttpSplitChangeFetcher fetcher = HttpSplitChangeFetcher.create(httpClient, rootTarget, metrics);
        Assert.assertThat(fetcher.getTarget().toString(), Matchers.is(Matchers.equalTo("https://api.split.io/api/splitChanges")));
    }

    @Test
    public void testCustomURLNoPathNoBackslash() throws URISyntaxException {
        URI rootTarget = URI.create("https://kubernetesturl.com/split");
        CloseableHttpClient httpClient = HttpClients.custom().build();
        Metrics.NoopMetrics metrics = new Metrics.NoopMetrics();
        HttpSplitChangeFetcher fetcher = HttpSplitChangeFetcher.create(httpClient, rootTarget, metrics);
        Assert.assertThat(fetcher.getTarget().toString(), Matchers.is(Matchers.equalTo("https://kubernetesturl.com/split/api/splitChanges")));
    }

    @Test
    public void testCustomURLAppendingPath() throws URISyntaxException {
        URI rootTarget = URI.create("https://kubernetesturl.com/split/");
        CloseableHttpClient httpClient = HttpClients.custom().build();
        Metrics.NoopMetrics metrics = new Metrics.NoopMetrics();
        HttpSplitChangeFetcher fetcher = HttpSplitChangeFetcher.create(httpClient, rootTarget, metrics);
        Assert.assertThat(fetcher.getTarget().toString(), Matchers.is(Matchers.equalTo("https://kubernetesturl.com/split/api/splitChanges")));
    }

    @Test
    public void testCustomURLAppendingPathNoBackslash() throws URISyntaxException {
        URI rootTarget = URI.create("https://kubernetesturl.com/split");
        CloseableHttpClient httpClient = HttpClients.custom().build();
        Metrics.NoopMetrics metrics = new Metrics.NoopMetrics();
        HttpSplitChangeFetcher fetcher = HttpSplitChangeFetcher.create(httpClient, rootTarget, metrics);
        Assert.assertThat(fetcher.getTarget().toString(), Matchers.is(Matchers.equalTo("https://kubernetesturl.com/split/api/splitChanges")));
    }

}
