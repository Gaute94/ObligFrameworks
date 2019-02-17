package no.oslomet.springbootdemo.repository;

import no.oslomet.springbootdemo.model.Building;
import no.oslomet.springbootdemo.model.Order;
import no.oslomet.springbootdemo.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
