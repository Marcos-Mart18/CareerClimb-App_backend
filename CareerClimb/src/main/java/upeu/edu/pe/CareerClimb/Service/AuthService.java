package upeu.edu.pe.CareerClimb.Service;

import upeu.edu.pe.CareerClimb.dto.LoginDto;
import upeu.edu.pe.CareerClimb.dto.RegisterDto;
import upeu.edu.pe.CareerClimb.Entity.Usuario;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    public Usuario findUserByUsername(String username);
}
