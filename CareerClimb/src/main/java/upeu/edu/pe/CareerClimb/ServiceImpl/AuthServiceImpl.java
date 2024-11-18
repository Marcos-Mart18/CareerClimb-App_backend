package upeu.edu.pe.CareerClimb.ServiceImpl;

import upeu.edu.pe.CareerClimb.config.JwtTokenProvider;
import upeu.edu.pe.CareerClimb.dto.LoginDto;
import upeu.edu.pe.CareerClimb.dto.RegisterDto;
import upeu.edu.pe.CareerClimb.Entity.Rol;
import upeu.edu.pe.CareerClimb.Entity.Usuario;
import upeu.edu.pe.CareerClimb.Entity.UsuarioRol;
import upeu.edu.pe.CareerClimb.Repository.RolRepository;
import upeu.edu.pe.CareerClimb.Repository.UsuarioRepository;
import upeu.edu.pe.CareerClimb.Service.AuthService;
import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Override
    public String login(LoginDto loginDto) {
        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 02 - Busca al usuario para obtener el nombre completo
        Usuario usuario = usuarioRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String nombreCompleto = usuario.getPersona().getNombres() + " " + usuario.getPersona().getApellidos();

        // 03 - Genera el token incluyendo el nombre completo
        return jwtTokenProvider.generateToken(authentication, nombreCompleto);
    }


    @Override
    public String register(RegisterDto registerDto) {
        // Verifica si el usuario ya existe
        if (usuarioRepository.existsByUsername(registerDto.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }

        // Crea una nueva entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setUsername(registerDto.getUsername());
        usuario.setEmail(registerDto.getEmail());

        // Encripta la contraseña y la establece en el usuario
        usuario.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // Asigna el rol especificado en registerDto
        Rol userRole = rolRepository.findByNombre(registerDto.getRoleName())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + registerDto.getRoleName()));


        
        // Crea una nueva instancia de UsuarioRol y establece el usuario y el rol
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(userRole);

        // Agrega el UsuarioRol a la lista de roles del usuario
        usuario.setUsuarioRoles(Collections.singletonList(usuarioRol));

        // Guarda el usuario en la base de datos
        usuarioRepository.save(usuario);

        // Devuelve un mensaje de éxito
        return "Usuario registrado con éxito con el rol: " + registerDto.getRoleName();
    }

	@Override
	// Método adicional para obtener el usuario por nombre de usuario
    public Usuario findUserByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
