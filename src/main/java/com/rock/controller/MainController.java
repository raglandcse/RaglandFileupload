package com.rock.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rock.model.UserDetails;
import com.rock.service.UserDetailsService;

/**
 * 
 * @author Ragland
 *
 */
@Controller
public class MainController {

	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/index")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:status";
		}

		try {
			byte[] bytes = file.getBytes();
			System.out.println(file.getOriginalFilename());
			Path path = Paths.get(file.getOriginalFilename());
			Files.write(path, bytes);
			BufferedReader br = new BufferedReader(new FileReader(file.getOriginalFilename()));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] lines = line.split(",");
				UserDetails userDetails = new UserDetails();

				userDetails.setUserid(lines[0]);
				userDetails.setUsername(lines[1]);
				userDetails.setFirstname(lines[2]);
				userDetails.setLastname(lines[3]);
				if (lines.length == 5)
					userDetails.setPassword(lines[4]);
				userDetailsService.addUserDetails(userDetails);
				System.out.println(line);
			}
			JSONObject obj = new JSONObject();
			JSONArray array = new JSONArray();

			List<UserDetails> list = userDetailsService.getAllUserDetails();
			for (int i = 0; i < list.size(); i++) {
				int j = 1;
				obj = new JSONObject();
				if (list.get(i).getUserid() == null || list.get(i).getUserid().equalsIgnoreCase(" ")) {
					obj.put("Sno", "false");
					j = 0;
				}
				else
					obj.put("Sno", "true");

				if (list.get(i).getUsername() == null || list.get(i).getUsername().equalsIgnoreCase(" ")) {
					obj.put("Usename", "false");
					j = 0;
				} else
					obj.put("Usename", "true");

				if (list.get(i).getFirstname() == null || list.get(i).getFirstname().equalsIgnoreCase(" ")) {
					j = 0;
					obj.put("Firstname", "false");
				} else
					obj.put("Firstname", "true");
				if (list.get(i).getLastname() == null || list.get(i).getLastname().equalsIgnoreCase(" ")) {
					j = 0;
					obj.put("Lastname", "false");
				} else
					obj.put("Lastname", "true");
				if (list.get(i).getPassword() == null || list.get(i).getPassword().equalsIgnoreCase(" ")) {
					j = 0;
					obj.put("Password", "false");
				} else
					obj.put("Password", "true");
				if (j == 0)
					obj.put("Status", "false");
				else
					obj.put("Status", "true");

				array.put(obj);

			}
			System.out.println(array);
			redirectAttributes.addFlashAttribute("message", array);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/status";
	}

	@GetMapping("/status")
	public String uploadStatus() {
		return "status";
	}

}