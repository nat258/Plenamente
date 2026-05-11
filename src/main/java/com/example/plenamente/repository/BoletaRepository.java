package com.example.plenamente.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
import org.springframework.stereotype.Repository;
import com.example.plenamente.model.Boleta;

@Repository
public interface BoletaRepository extends JpaRepository<Boleta,Integer> {

<<<<<<< HEAD
    //Busqueda por tipo de pago (Efectivo o Tarjeta)
    List<Boleta> findByTipoPago(String tipoPago);
    //Busqueda por monto exacto 
    List<Boleta> findByMonto(Integer monto);
    //Busqueda por monto mayor a
    @Query("SELECT b FROM Boleta b WHERE b.monto > : minimo")
    List<Boleta> findByPorMontoSuperior(@Param("minimo")Integer minimo);
=======

    //Busqueda por monto exacto 
    List<Boleta> findByMonto(Integer monto);


>>>>>>> f6c8ec0 (Inclusion de ultimos services y controller  enn rama nataly)
    
}

