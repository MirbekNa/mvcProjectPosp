package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Hospital;
import peaksoft.exeptions.MyException;
import peaksoft.service.HospitalService;

@Service
@Controller
@RequestMapping("/hospitals")
public class HospitalApi {


    private final HospitalService hospitalService;

    @Autowired
    public HospitalApi(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("newHospital", new Hospital());
        return "/hospitals/newHospital";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("newHospital") Hospital hospital) throws MyException {
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping
    public String getAllHospitals(Model model) throws MyException {
        model.addAttribute("hospitals", hospitalService.getAllHospitals());
        return "/hospitals/mainHospital";
    }

    @GetMapping("/{hospitalId}/edit")
    public String edit(@PathVariable("hospitalId") Long hospitalId, Model model) throws MyException {
        Hospital hospital = hospitalService.getHospitalById(hospitalId);
        model.addAttribute("hospital", hospital);
        return "/hospitals/updateHospital";
    }

    @PutMapping("/{hospitalId}/update")
    public String update(@ModelAttribute("hospital") Hospital hospital, @PathVariable("hospitalId") Long hospitalId) throws MyException {
        hospitalService.updateHospital(hospitalId, hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/{hospitalId}/deleteHospital")
    public String delete(@PathVariable("hospitalId") Long hospitalId) throws MyException {
        hospitalService.deleteHospital(hospitalId);
        return "redirect:/hospitals";
    }
}
