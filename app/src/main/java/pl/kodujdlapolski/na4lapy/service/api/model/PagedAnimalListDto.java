package pl.kodujdlapolski.na4lapy.service.api.model;

import java.util.List;

import pl.kodujdlapolski.na4lapy.model.Animal;

public class PagedAnimalListDto {

    private List<Animal> data;
    private int total;

    public List<Animal> getData() {
        return data;
    }

    public void setData(List<Animal> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
