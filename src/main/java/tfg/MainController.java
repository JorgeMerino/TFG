package tfg;

import tfg.entidades.Password;
import tfg.entidades.Usuario;
import tfg.repositorios.RepositorioPassword;
import tfg.repositorios.RepositorioUsuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller    // This means that this class is a Controller
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private RepositorioUsuario repoUsuario;
	private RepositorioPassword repoPassword;
	
	@GetMapping("/")
    public String mostrarIndex(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "index";
    }
	
	@GetMapping("/crear_cuenta")
    public String mostrarCrearCuenta(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "crearCuenta";
    }
	
	@PostMapping("/iniciar_sesion") // Map ONLY GET Requests
	public String iniciarSesion (@Valid @ModelAttribute Usuario usuario,
			BindingResult errores) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		/*n.setNombre(name);
		n.setEmail(email);*/
		return "principal";
	}
	
	@PostMapping("/nuevo_usuario") // Map ONLY GET Requests
	public String nuevoUsuario (@Valid @ModelAttribute Usuario usuario,
			BindingResult errores) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		/*n.setNombre(name);
		n.setEmail(email);*/
		Password password = new Password(usuario.getPassword().getPassword());
		repoUsuario.save(usuario);
		repoPassword.save(password);
		return "index";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Usuario> getAllUsers() {
		// This returns a JSON or XML with the users
		return repoUsuario.findAll();
	}
}
