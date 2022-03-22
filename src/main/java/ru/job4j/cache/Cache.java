package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache  {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();


    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
       return memory.computeIfPresent(model.getId(), (id, base) -> {
            Base storedCache = memory.get(model.getVersion());
            if (storedCache.getVersion() != model.getVersion()) {
                throw new OptimisticException("Cache version is not equals");
            }
            Base updatedCache = new Base(id, model.getVersion() + 1);
            updatedCache.setName(model.getName());
            return updatedCache;
        }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId());
    }

    public Base getBase(int id) {
        return memory.get(id);
    }
}
