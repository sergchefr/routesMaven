package ru.ifmo.server.coll;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetHandlerTest {

    @Test
    void addToEmptyColl() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            coll.add(new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f));
        }catch (Exception e){
            fail();
        }
        assertEquals(coll.getAllRoutes()[0].getId(), 1);
        assertEquals(coll.getAllRoutes()[0].getDistance(), 345f);
        assertEquals(coll.getAllRoutes()[0].getFromLocation(), new Location(1, 4, 3.5f, "free"));
        assertEquals(coll.getAllRoutes()[0].getToLocation(), new Location(1, 4, 4.6f, "tyu"));

        //coll.add(new Route("rt1", new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f));
    }
    @Test
    void addwithsameId(){
        TreeSetHandler coll = new TreeSetHandler();
        try {
            coll.add(new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f));
            coll.add(new Route(1L,"rt2",new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 345f));
        }catch (Exception e){
            fail();
        }
        assertEquals(2, coll.getAllRoutes().length);
        assertNotEquals(coll.getAllRoutes()[0],coll.getAllRoutes()[1]);
    }

    @Test
    void update() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);
            coll.update(1L, b);
            assertNotEquals(a,b);
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void removeByIdElementExists() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);

            coll.removeById(1);
            assertEquals(0, coll.size());
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void removeByIdElementNotExists() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);

            coll.removeById(1000);
            assertEquals(1, coll.size());
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void clear() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);
            coll.add(b);

            coll.clear();
            assertEquals(0,coll.size());
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void getAllRoutes() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);
            coll.add(b);

            Route[] arr = coll.getAllRoutes();
            if(arr[0].equals(a))assertEquals(b,arr[1]);
            else{
                assertEquals(a,arr[1]);
                assertEquals(b,arr[0]);
            }
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void addIfMaxIsMax() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 1f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);
            coll.addIfMax(b);

            assertEquals(2,coll.size());
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void addIfMaxNotMax() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 1f);
            coll.add(a);
            coll.addIfMax(b);

            assertEquals(1,coll.size());
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void addIfMinNotMin() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 1f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 345f);
            coll.add(a);
            coll.addIfMin(b);

            assertEquals(1,coll.size());
        }catch (Exception e){
            fail();
        }
    }

    @Test
    void addIfMinisMin() {
        TreeSetHandler coll = new TreeSetHandler();
        try {
            Route a = new Route(1L,"rt1",new Date(), new Location(1, 4, 3.5f, "free"), new Location(1, 4, 4.6f, "tyu"), 345f);
            Route b =new Route(5L, "rt2", new Date(), new Location(1, 4, 3.5f, "frwqere"), new Location(1, 4, 4.6f, "tyu"), 1f);
            coll.add(a);
            coll.addIfMin(b);

            assertEquals(2,coll.size());
        }catch (Exception e){
            fail();
        }
    }
}