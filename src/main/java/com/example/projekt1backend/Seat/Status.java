package com.example.projekt1backend.Seat;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    private String name;

    @OneToMany(mappedBy = "seatStatus")
    private List<Seat> seatId;

    public Status(String name) {
        this.name = name;
    }

    public Status() {}

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeatId() {
        return seatId;
    }

    public void setSeatId(List<Seat> seatId) {
        this.seatId = seatId;
    }
}
