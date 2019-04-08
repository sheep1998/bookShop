package com.example.bookshop.Controller;

import org.neo4j.driver.v1.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.neo4j.driver.v1.Values.parameters;


@RestController
@RequestMapping(value = "neo4j")
public class Neo4jController {

    private Driver createDrive(){
        return GraphDatabase.driver("http://localhost:7474", AuthTokens.basic("neo4j","pp1819sdf"));
    }

    @RequestMapping(value="save")
    public void save(){
        try{
            Driver driver = createDrive();
            Session session = driver.session();
            session.run("CREATE (LIHU:User{user:{user},role:{role}})",
                    parameters("user",1,"role",0));
            session.run("CREATE (CHYU:User{user:{user},role:{role}})",
                    parameters("user",2,"role",1));
            session.run("CREATE (FUSHENG:Book{coding:{coding}})",
                    parameters("coding",1));
            session.run("CREATE (YUELIANG:Book{coding:{coding}})",
                    parameters("coding",2));
            session.close();
            driver.close();
        }catch (Exception e){
            System.out.println("err save graphDB");
        }
    }

    @RequestMapping(value = "relate")
    public void relate(){
        try{
            Driver driver = createDrive();
            Session session = driver.session();
            session.run("MATCH(e:User),(f:User) WHERE e.user=1 AND f.user=2 CREATE (e)-[:LOVE]->(f)");
            session.run("MATCH(a:User),(b:Book) WHERE a.user=1 AND b.coding=1 CREATE (a)-[:ENJOY]->(b)");
            session.run("MATCH(c:User),(d:Book) WHERE c.user=2 AND d.coding=2 CREATE (c)-[:ENJOY]->(d)");
            session.close();
            driver.close();
        }catch (Exception e){
            System.out.println("err relate graphDB");
        }
    }


}
