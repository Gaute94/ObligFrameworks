package no.oslomet.springbootdemo.repository;

import no.oslomet.springbootdemo.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
