package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.Usuario;
import CoreMVC.Web.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuarioToLogin) {
        return usuarioService.login(usuarioToLogin.getEmail(), usuarioToLogin.getContrasenia());
    }

    @PostMapping("/registrar")
    public String registrarUsuario(@RequestBody Usuario newUsuario) {
        if (!newUsuario.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "El formato del email no es válido";
        }
        return usuarioService.registrarUsuario(
                newUsuario.getEmail(),
                newUsuario.getPrimerNombre(),
                newUsuario.getApellido(),
                newUsuario.getContrasenia(),
                newUsuario.getFechaNacimiento());
    }

}
