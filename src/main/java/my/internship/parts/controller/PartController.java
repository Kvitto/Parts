package my.internship.parts.controller;


import my.internship.parts.model.Part;
import my.internship.parts.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PartController {
    private PartService partService;

    @Autowired(required = true)
    @Qualifier(value = "partService")
    public void setPartService(PartService partService) {
        this.partService = partService;
    }

    @RequestMapping(value = "parts", method = RequestMethod.GET)
    public String listParts(Model model){
        model.addAttribute("part", new Part());
        model.addAttribute("listParts", this.partService.listParts());
        model.addAttribute("minBasePart", this.partService.getMinBasePart());
        model.addAttribute("totalPages", this.partService.getTotalPages());
        model.addAttribute("curPage", this.partService.getNoPage());
        model.addAttribute("opt", this.partService.getOption());
        return "parts";
    }

    @RequestMapping("/parts/{id}/{noPage}")
    public String listParts(@PathVariable("id") int id, @PathVariable("noPage") int noPage){
        this.partService.setOption(id);
        this.partService.setNoPage(noPage);
        return "redirect:/parts";
    }

    @RequestMapping(value = "/parts/add", method = RequestMethod.POST)
    public String addPart(@ModelAttribute("part") Part part){
        if(part.getId() == 0){
            this.partService.addPart(part);
        }else{
            this.partService.updatePart(part);
        }
        return "redirect:/parts";
    }

    @RequestMapping(value = "/parts/search", method = RequestMethod.POST)
    public String searchClubs(@RequestParam("keyWord") String keyWord){
        System.out.println("keyWord - "+keyWord);
        if(keyWord.equals(""))return "redirect:/parts";
        this.partService.setOption(1);
        this.partService.setSearchTitle(keyWord);
        return "redirect:/parts";
    }

    @RequestMapping("/remove/{id}")
    public String removePart(@PathVariable("id") int id){
        this.partService.removePart(id);
        return "redirect:/parts";
    }

    @RequestMapping("/edit/{id}")   //определяем место параметра в адресной строке - {id}
    public String editPart(@PathVariable("id") int id, Model model){    //аннотация @PathVariable, указывающая на то, что данный параметр получается из адресной строки
        model.addAttribute("listParts", this.partService.listParts());
        model.addAttribute("part", this.partService.getPartById(id));
        model.addAttribute("minBasePart", this.partService.getMinBasePart());
        model.addAttribute("totalPages", this.partService.getTotalPages());
        model.addAttribute("curPage", this.partService.getNoPage());
        model.addAttribute("opt", this.partService.getOption());
        return "parts";
    }



}
