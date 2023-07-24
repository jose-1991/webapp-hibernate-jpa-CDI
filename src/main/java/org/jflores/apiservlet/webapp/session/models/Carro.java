package org.jflores.apiservlet.webapp.session.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemCarro){
        if (items.contains(itemCarro)) {
            Optional<ItemCarro> optionalItemCarro = items.stream().filter(i -> i.equals(itemCarro)).findAny();
            if (optionalItemCarro.isPresent()){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad()+1);
            }
        }else {
            this.items.add(itemCarro);
        }
    }
    public List<ItemCarro> getItems() {
        return items;
    }
    public int getTotal(){
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }
}
