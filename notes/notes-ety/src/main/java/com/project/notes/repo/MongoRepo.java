package com.project.notes.repo;

import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class MongoRepo {

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * Qry by criteria.
     *
     * @param <T>      the generic type
     * @param criteria the criteria
     * @param pClass   the class
     * @return the list
     */
    public <T> List<T> qryByCriteria(Criteria criteria, Class<T> pClass) {
        List<T> page = null;
        int retryCnt = 1;
        do {
            try {
                Query qry = new Query(criteria);
                page = mongoTemplate.find(qry, pClass);
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
        return page;
    }

    /**
     * Removes the by criteria.
     *
     * @param <T>      the generic type
     * @param criteria the criteria
     * @param pClass   the class
     * @return the long
     */
    public <T> long removeByCriteria(Criteria criteria, Class<T> pClass) {
        long delCnt = 0;
        int retryCnt = 1;
        do {
            try {
                Query qry = new Query(criteria);
                delCnt = mongoTemplate.remove(qry, pClass).getDeletedCount();
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
        return delCnt;
    }

    /**
     * Qry by criteria.
     *
     * @param <T>      the generic type
     * @param criteria the criteria
     * @param pClass   the class
     * @return the list
     */
    public <T> T qryOneByCriteria(Criteria criteria, Class<T> pClass) {
        T page = null;
        int retryCnt = 1;
        do {
            try {
                Query qry = new Query(criteria);
                page = mongoTemplate.findOne(qry, pClass);
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
        return page;
    }

    /**
     * Qry for count.
     *
     * @param <T>      the generic type
     * @param criteria the criteria
     * @param pClass   the class
     * @return the long
     */
    public <T> long qryForCount(Criteria criteria, Class<T> pClass) {
        long count = 0;
        int retryCnt = 1;
        do {
            try {
                count = mongoTemplate.count(new Query(criteria).skip(-1).limit(-1), pClass);
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
        return count;
    }

    /**
     * Qry for aggregated objects.
     *
     * @param <T>                   the generic type
     * @param <R>                   the generic type
     * @param aggQry                the agg qry
     * @param domainClass           the domain class
     * @param resultProjectionClass the result projection class
     * @return the list
     */
    public <T, R> List<R> qryForAggregatedObjects(Aggregation aggQry, Class<T> domainClass, Class<R> resultProjectionClass) {
        List<R> aggResults = null;
        int retryCnt = 1;
        do {
            try {
                aggResults = mongoTemplate.aggregate(aggQry, domainClass, resultProjectionClass).getMappedResults();
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
        return aggResults;
    }

    /**
     * Qry for aggregated object.
     *
     * @param <T>                   the generic type
     * @param <R>                   the generic type
     * @param aggQry                the agg qry
     * @param domainClass           the domain class
     * @param resultProjectionClass the result projection class
     * @return the r
     */
    public <T, R> R qryForAggregatedObject(Aggregation aggQry, Class<T> domainClass, Class<R> resultProjectionClass) {
        R aggResult = null;
        int retryCnt = 1;
        do {
            try {
                aggResult = mongoTemplate.aggregate(aggQry, domainClass, resultProjectionClass).getUniqueMappedResult();
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
        return aggResult;
    }

    /**
     * Save.
     *
     * @param <T>         the generic type
     * @param savableUnit the savable unit
     */
    public <T> void save(T savableUnit) {
        int retryCnt = 1;
        do {
            try {
                mongoTemplate.save(savableUnit);
            } catch (MongoException e) {
                retryCnt++;
                if (retryCnt > 3) {
                    throw e;
                }
            }
        } while (retryCnt != 1 && retryCnt <= 3);
    }

    /**
     * Save all.
     *
     * @param <T>          the generic type
     * @param savableUnits the savable units
     */
    public <T> void saveAll(List<T> savableUnits) {
        savableUnits.forEach(su -> {
            int retryCnt = 1;
            do {
                try {
                    mongoTemplate.save(su);
                } catch (MongoException e) {
                    retryCnt++;
                    if (retryCnt > 3) {
                        throw e;
                    }
                }
            } while (retryCnt != 1 && retryCnt <= 3);
        });
    }

    /**
     * Save all.
     *
     * @param <T>          the generic type
     * @param savableUnits the savable units
     */
    public <T> void saveAll(Iterable<T> savableUnits) {
        savableUnits.forEach(su -> {
            int retryCnt = 1;
            do {
                try {
                    mongoTemplate.save(su);
                } catch (MongoException e) {
                    retryCnt++;
                    if (retryCnt > 3) {
                        throw e;
                    }
                }
            } while (retryCnt != 1 && retryCnt <= 3);
        });
    }

}
