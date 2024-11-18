package upeu.edu.pe.CareerClimb.Controller;


import upeu.edu.pe.CareerClimb.dto.LoginDto;
import upeu.edu.pe.CareerClimb.dto.RegisterDto;
import upeu.edu.pe.CareerClimb.Entity.Usuario;
import upeu.edu.pe.CareerClimb.Service.AuthService;
import upeu.edu.pe.CareerClimb.Service.IEmailService;

import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private IEmailService emailService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        // 01 - Autentica al usuario y genera el token
        String token = authService.login(loginDto);

        // 02 - Busca el usuario en la base de datos para obtener su información
        Usuario usuario = authService.findUserByUsername(loginDto.getUsername());

        // 03 - Obtiene el nombre y apellido desde la relación con Persona
        String nombreCompleto = usuario.getPersona().getNombres() + " " + usuario.getPersona().getApellidos();

        // 04 - Configura los detalles del correo
        String userEmail = usuario.getEmail(); // Correo electrónico del usuario
        String subject = "Token de acceso"; // Asunto con el nombre del usuario
        String message = "Querido " + nombreCompleto + ",\n\nEste es tu token de autorización:\n\n" + token + "\n\nPor favor, mantenlo seguro.\n\nSi no fuiste tú, preocupate";

        // 05 - Prepara el destinatario y envía el correo
        String[] toUser = { userEmail };
        emailService.sendEmail(toUser, subject, message);

        // 06 - Retorna el token y un mensaje de éxito
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", token); // Incluye el token en la respuesta
        response.put("message", "Tu token ha sido enviado a tu correo registrado.");

        return ResponseEntity.ok(response);
    }


    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        // Llama al método register del servicio
        String result = authService.register(registerDto);

        // Retorna la respuesta de éxito
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
