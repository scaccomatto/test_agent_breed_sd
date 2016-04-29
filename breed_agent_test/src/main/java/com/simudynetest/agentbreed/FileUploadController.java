package com.simudynetest.agentbreed;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simudynetest.model.Agent;
import com.simudynetest.utils.CsvFileReader;

/*
 * This is just the controller that was already in the spring.io project  http://spring.io/guides/gs/uploading-files/
 * 
 * basically I get and the brand factor and  the file, parse the file to obtain the list of Agents 
 * and then call AgentService that is the class that implements this solution. The output is sent to the front end
 * 
 * **/


@Controller
public class FileUploadController {
	
	@Autowired AgentService agentService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String provideUploadInfo(Model model) {

		return "uploadForm";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public String handleFileUpload(@RequestParam("brandFactor") String bf, 
								   @RequestParam("file") MultipartFile file,
								   RedirectAttributes redirectAttributes) {
		double brandFactor = 0;
		int years = 15;
		try{
			brandFactor = Double.parseDouble(bf);
			if(brandFactor<0.1 || brandFactor>2.9){
				redirectAttributes.addFlashAttribute("message", "brand factor should have a range (0.1 -> 2.9) ");
				return "redirect:/";
			}
		}catch(Exception e){
			redirectAttributes.addFlashAttribute("message", "brand factor should have a range (0.1 -> 2.9)");
			return "redirect:/";
		}


		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(Application.ROOT + "/" + "test")));
                FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();
				BufferedReader br = new BufferedReader( new FileReader(Application.ROOT + "/" + "test"));
				
				//Parsing the file
				List<Agent> agentsList = CsvFileReader.readWithCsvBeanReader(br);
				//calculating the output
				Map <String, String> result = agentService.calculateAgentsBreedFunctional(agentsList, brandFactor, years);
				
				redirectAttributes.addFlashAttribute("breed_CAgentsLost",result.get("breed_CAgentsLost"));
				redirectAttributes.addFlashAttribute("breed_CAgentsGain",result.get("breed_CAgentsGain"));
				redirectAttributes.addFlashAttribute("breed_CAgentsRegain",result.get("breed_CAgentsRegain"));
			}
			catch (Exception e) {
				redirectAttributes.addFlashAttribute("message",
						"Error during uploading " + e.getMessage());
			}
		}
		else {
			redirectAttributes.addFlashAttribute("message",
					"You failed to upload  because the file was empty");
		}

		return "redirect:/";
	}

}
