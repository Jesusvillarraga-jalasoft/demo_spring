package com.tonolandia.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private static final Map<String,Integer> HP = new HashMap<>();
    private static final Map<String,String> TYPE = new HashMap<>();

    static class GameUtils {
        static int calcDamage(String type, String mode) {
            if ("GUERRERO".equals(type)) return 12;
            if ("MAGO".equals(type)) return 8;
            return 5;
        }
        static int clampHp(int v){ if(v<0) return 0; if(v>100) return 100; return v; }
    }

    @PostMapping("/party")
    public ResponseEntity<Map<String,Object>> create(@RequestBody Map<String,String> body) {
        String type = body.getOrDefault("type","GUERRERO");
        String name = body.get("name");
        TYPE.put(name, type);
        HP.put(name, 100);
        Map<String,Object> res = new LinkedHashMap<>();
        res.put("name", name);
        res.put("type", type);
        res.put("hp", 100);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/combat/attack")
    public Map<String,String> attack(@RequestBody Map<String,String> body) {
        String attacker = body.get("attacker");
        String defender = body.get("defender");
        String mode = body.getOrDefault("mode", "AUTO");
        String atType = TYPE.get(attacker);

        int dmg = GameUtils.calcDamage(atType, mode);
        int hpDef = HP.getOrDefault(defender,0);
        hpDef = GameUtils.clampHp(hpDef - dmg);
        HP.put(defender, hpDef);

        return Map.of("log", attacker + " ataca a " + defender + " por " + dmg + " (HP " + defender + "=" + hpDef + ")");
    }

    @PostMapping("/combat/heal")
    public Map<String,String> heal(@RequestBody Map<String,Object> body) {
        String who = (String) body.get("name");
        int amount = (int) body.getOrDefault("amount", 0);
        int cur = HP.getOrDefault(who, 0);
        cur = GameUtils.clampHp(cur + amount);
        HP.put(who, cur);
        return Map.of("log", who + " se cura +" + amount + " (HP=" + cur + ")");
    }

    @GetMapping("/status")
    public List<Map<String,Object>> status() {
        List<Map<String,Object>> list = new ArrayList<>();
        for (String n : HP.keySet()) {
            Map<String,Object> m = new LinkedHashMap<>();
            m.put("name", n);
            m.put("type", TYPE.get(n));
            m.put("hp", HP.get(n));
            list.add(m);
        }
        return list;
    }
}
