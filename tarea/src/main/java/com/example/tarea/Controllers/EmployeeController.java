package com.example.tarea.Controllers;

import com.example.tarea.Entities.Department;
import com.example.tarea.Entities.Employee;
import com.example.tarea.Entities.Job;
import com.example.tarea.Repositories.BaseRepository;
import com.example.tarea.Repositories.DepartmentRepository;
import com.example.tarea.Repositories.EmployeeRepository;
import com.example.tarea.Repositories.JobRepository;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarea")
public class EmployeeController {

    final BaseRepository baseRepository;
    final EmployeeRepository employeeRepository;
    final JobRepository jobRepository;
    final DepartmentRepository departmentRepository;

    public EmployeeController(BaseRepository baseRepository, EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.baseRepository = baseRepository;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/empleados")
    public String sub1(Model model) {
        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("empleados", employeeList);
        return "Employee/Empleados";
    }



    @GetMapping("/guardarEmpleado")
    public String guardar(@ModelAttribute("employee") Employee employee, Model model) {
        List<Job> jobList = jobRepository.findAll();
        List<Department> departmentList = departmentRepository.findAll();
        List<Employee> employeeList = employeeRepository.findAll();

        model.addAttribute("jobs", jobList);
        model.addAttribute("departments", departmentList);
        model.addAttribute("jefes", employeeList);
        return "Employee/formularioAgregar";
    }

    @GetMapping("/editarEmployee")
    public String editar(@ModelAttribute("employee") Employee employee, Model model, @RequestParam("idEmployee") Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            model.addAttribute("employee", employeeOptional.get());
            List<Job> jobList = jobRepository.findAll();
            List<Department> departmentList = departmentRepository.findAll();
            List<Employee> employeeList = employeeRepository.findAll();

            model.addAttribute("jobs", jobList);
            model.addAttribute("departments", departmentList);
            model.addAttribute("jefes", employeeList);
            return "Employee/formularioAgregar";
        } else {
            return "redirect:/tarea/empleados";
        }

    }

    @PostMapping("/guardar")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employee.setHire_date("1994-06-07 00:00:00");
        try {
            employeeRepository.save(employee);
            return "redirect:/tarea/empleados";
        } catch (Exception e) {
            return "redirect:/empleados"; // Redirigir a una página de error
        }
    }

    @GetMapping("/filtrar")
    public String filtrar(@RequestParam("nombre") String palabra, Model model) {
        List<Employee> employeeList = employeeRepository.searchEmployees(palabra);
        model.addAttribute("empleados", employeeList);
        return "Employee/Empleados";
    }



    /*GetMapping("/sub2")
    public String sub2(@RequestParam("tipo") Long  tipo,
                        @RequestParam("color") Long  color,
                        @RequestParam("ocasion") Long  ocasion, Model model){
        //SUMAR LISTAS
        List<Flores> floresLis1 = floresRepository.findAll();
        List<Flores> floresLis2 = floresRepository.findAll();
        List<Flores> listaSumada = new ArrayList<>(floresLis1);

        model.addAttribute("cosas", listaSumada);
        return "plantilla";
     */

    /*@GetMapping("/editar")
    public String editarBase(Model model,
                                  @RequestParam("idEmployee") Integer id) {
        List<Job> listaJobs = employeesJobRepository.findAll();
        model.addAttribute("listaJobs", listaJobs);
        Optional<Employee> optionalEmployee = employeesRepository.findById(id);

        if(optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            model.addAttribute("employee", employee);
            return "catalogo";
        }else{
            return "redirect:/tarea/listar";
        }
    }*/

    /*@PostMapping("/guardar")
    public String guardar (Employee employee) {
        employeesRepository.save(employee);
        return "redirect:/tarea/listar";
    }*/

    /*@GetMapping("/borrar")
    public String borrarEmpleado(Model model, @RequestParam("idEmployee") int id, RedirectAttributes redirectAttributes) {
        try {
            employeesRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Se borró el empleado");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "No se pudo borrar el empleado");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }
        return "redirect:/employee/listar";
    }*/

}
