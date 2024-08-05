package com.keniding.reactor_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private String id;
    private LocalDateTime time;

    public Flux<Sale> sales() {
        List<Sale> sales = new ArrayList<>();
        sales.add(new Sale("F001", LocalDateTime.now()));
        sales.add(new Sale("F002", LocalDateTime.now()));

        return Flux.fromIterable(sales);
    }

}
