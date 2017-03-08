package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

import java.util.List;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("/")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, Model model) {

        Integer created = signupRepository.insecureSave(new Signup(name, address));

        // brutal way to retrieve last created object
        // won't work in a real concurrent environment
        Signup signup = signupRepository.findAll(new PageRequest(0, 1,
                Sort.Direction.DESC, "id")).getContent().get(0);

        model.addAttribute("signup", signup);
        model.addAttribute("created", created);

        return "done";
    }

//    @ResponseBody
//    @RequestMapping(value = "/signups", method = RequestMethod.GET)
//    public List<Signup> list() {
//        return signupRepository.findAll();
//    }

    @ResponseBody
    @RequestMapping(value = "/signups/{id}", method = RequestMethod.GET)
    public List<Signup> read() {
        return signupRepository.findAll();
    }

}
