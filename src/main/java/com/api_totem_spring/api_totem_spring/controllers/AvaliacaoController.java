package com.api_totem_spring.api_totem_spring.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("avaliacao")
@CrossOrigin("*")
public class AvaliacaoController {

    @Autowired
    private EntityManager em;

    @Transactional
    @PostMapping("salvar/{tipo}/{id}")
    public void salvar(@PathVariable String tipo, @PathVariable Integer id) {
        String query = "";
        if (tipo.equals("bom")) {
            query = "UPDATE avaliacao SET bom = bom + 1 WHERE id = :id";
        }
        if (tipo.equals("regular")) {
            query = "UPDATE avaliacao SET regular = regular + 1 WHERE id = :id";
        }
        if (tipo.equals("otimo")) {
            query = "UPDATE avaliacao SET otimo = otimo + 1 WHERE id = :id";
        }
        var q = em.createNativeQuery(query);
        q.setParameter("id", id);
        var result = q.executeUpdate();
        System.out.println(result);
    }
    

}
