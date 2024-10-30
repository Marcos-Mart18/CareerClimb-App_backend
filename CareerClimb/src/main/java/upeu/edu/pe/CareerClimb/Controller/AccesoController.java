package upeu.edu.pe.CareerClimb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import upeu.edu.pe.CareerClimb.Entity.Acceso;
import upeu.edu.pe.CareerClimb.Service.AccesoService;

@RestController
@RequestMapping("/api/accesos")
@CrossOrigin(origins = "http://localhost:4200")
public class AccesoController {
    @Autowired
    private AccesoService accesoService;

    @GetMapping
    public ResponseEntity<List<Acceso>> getAll() {
        try {
			List<Acceso> a = accesoService.getAll();
			if (a.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(a, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acceso> getById(@PathVariable("id") Long id) {
        try {
			Acceso a = accesoService.getById(id).get();
			return new ResponseEntity<>(a, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @PostMapping
    public ResponseEntity<Acceso> create(@Validated @RequestBody Acceso acceso) {
    	try {
			Acceso a = accesoService.create(acceso);
			return new ResponseEntity<>(a, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Validated @RequestBody Acceso acceso) {
        Acceso a = accesoService.getById(id).get();
        if (a.getIdAcceso()>0) {
			return new ResponseEntity<>(accesoService.update(a), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Acceso> delete(@PathVariable("id") Long id) {
        try {
        	accesoService.delete(id);
        	return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
