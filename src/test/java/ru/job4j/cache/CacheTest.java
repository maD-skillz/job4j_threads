package ru.job4j.cache;

import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class CacheTest {

    @Test
    public void simpleCacheTest() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1, "BaseName");
        Base base2 = new Base(1, 1, "BaseName2");
        Base expectedBase = new Base(1, 2, "BaseName2");
        cache.add(base1);
        cache.update(base2);

        assertThat(cache.getBase(1).getId(), is(expectedBase.getId()));
        assertThat(cache.getBase(1).getVersion(), is(expectedBase.getVersion()));
        assertThat(cache.getBase(1).getName(), is(expectedBase.getName()));
    }

    @Ignore
    @Test
    public void simpleCacheTest2() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1, "BaseName");
        Base base2 = new Base(1, 1, "BaseName2");
        Base base3 = new Base(1, 2, "BaseName3");
        Base expectedBase = new Base(1, 3, "BaseName3");
        cache.add(base1);
        cache.update(base2);
        cache.update(base3);

        assertThat(cache.getBase(1).getId(), is(expectedBase.getId()));
        assertThat(cache.getBase(1).getVersion(), is(expectedBase.getVersion()));
        assertThat(cache.getBase(1).getName(), is(expectedBase.getName()));
    }

}