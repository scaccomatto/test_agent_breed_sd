package com.simudynetest.agentbreed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.simudynetest.model.Agent;
import com.simudynetest.model.AgentBreed;

@Service
public class AgentServiceImpl implements AgentService{
	
	/*
	 * This is the 
	 * 
	 * */
	
	public synchronized Map <String, String> calculateAgentsBreed(List<Agent> agents, double brandFactor, int years){
		
		List<Agent> breedCAgentsLost = new ArrayList<Agent>();
		List<Agent> breedCAgentsGain = new ArrayList<Agent>();
		List<Agent> breedCAgentsRegained = new ArrayList<Agent>();
		
		Map <String, String> result = new HashMap<String, String>();
		
		for(Agent ag : agents){
			
			if(ag.isAuto_Renew()){
				boolean breedCLost = false;
				boolean breadCGain = false;
				AgentBreed agentsStartBreen = ag.getAgent_Breed();
				
				double affinity = ag.getPayment_at_Purchase()/
						((double)(ag.getAttribute_Price()+(2*ag.getAttribute_Promotions()*ag.getInertia_for_Switch())));
				for(int i=1; i<years+1; i++){
					// as the age is never used, I don't understand why I should increment it
					ag.setAge(ag.getAge()+1);
					// updating the breed, following the requirments
					if(affinity <(ag.getSocial_Grade()*ag.getAttribute_Brand())&& ag.getAgent_Breed().equals(AgentBreed.Breed_C)){
						ag.setAgent_Breed(AgentBreed.Breed_NC.name());
					}else{
						if(affinity <(ag.getSocial_Grade()*ag.getAttribute_Brand()*brandFactor)
								&& ag.getAgent_Breed().equals(AgentBreed.Breed_NC)){
							ag.setAgent_Breed(AgentBreed.Breed_C.name());
							breadCGain = true;
						}
					}
				}
				if(ag.getAgent_Breed().equals(AgentBreed.Breed_C)){
					if( agentsStartBreen.equals(AgentBreed.Breed_NC)){
						breedCAgentsGain.add(ag);
					}else{
						if(breadCGain && breedCLost){
							breedCAgentsRegained.add(ag);
						}
					}	
				}else{
					if(agentsStartBreen.equals(AgentBreed.Breed_C)){
						breedCAgentsLost.add(ag);
					}
				}		
			}
		}
		result.put("breed_CAgentsLost", Integer.toString(breedCAgentsLost.size()));
		result.put("breed_CAgentsGain", Integer.toString(breedCAgentsGain.size()));
		result.put("breed_CAgentsRegain", Integer.toString(breedCAgentsRegained.size()));
		
		
		return result;
	}
	/**
	 * As I know that you are programming in Scala, I've tried to to use the "functional programming" tools that Java 8 provide,
	 * but as I don't know that much about it, I think that this solution could be improved
	 * 
	 * **/
	
	public synchronized Map <String, String> calculateAgentsBreedFunctional(List<Agent> agents, double brandFactor, int years){
		
		List<Agent> breedCAgentsLost = new ArrayList<Agent>();
		List<Agent> breedCAgentsGain = new ArrayList<Agent>();
		List<Agent> breedCAgentsRegained = new ArrayList<Agent>();
		
		Map <String, String> result = new HashMap<String, String>();
		System.out.println("Total agents:" + agents.size());
		agents.stream().filter(agent -> agent.isAuto_Renew()).collect(Collectors.toList());
		agents.stream().filter(agent -> agent.isAuto_Renew()).forEach(ag ->{
			boolean breedCLost = false;
			boolean breadCGain = false;
			AgentBreed agentsStartBreen = ag.getAgent_Breed();
			double affinity = ag.getPayment_at_Purchase()/
					((double)(ag.getAttribute_Price()+(2*ag.getAttribute_Promotions()*ag.getInertia_for_Switch())));
			for(int i=1; i<years+1; i++){
				ag.setAge(ag.getAge()+1);
				
				if(affinity <(ag.getSocial_Grade()*ag.getAttribute_Brand())&& ag.getAgent_Breed().equals(AgentBreed.Breed_C)){
					ag.setAgent_Breed(AgentBreed.Breed_NC.name());
				}else{
					if(affinity <(ag.getSocial_Grade()*ag.getAttribute_Brand()*brandFactor)
							&& ag.getAgent_Breed().equals(AgentBreed.Breed_NC)){
						ag.setAgent_Breed(AgentBreed.Breed_C.name());
						breadCGain = true;
					}
				}
			}
			if(ag.getAgent_Breed().equals(AgentBreed.Breed_C)){
				if( agentsStartBreen.equals(AgentBreed.Breed_NC)){
					breedCAgentsGain.add(ag);
				}else{
					if(breadCGain && breedCLost){
						breedCAgentsRegained.add(ag);
					}
				}	
			}else{
				if(agentsStartBreen.equals(AgentBreed.Breed_C)){
					breedCAgentsLost.add(ag);
				}
			}
		});
		
		result.put("breed_CAgentsLost", Integer.toString(breedCAgentsLost.size()));
		result.put("breed_CAgentsGain", Integer.toString(breedCAgentsGain.size()));
		result.put("breed_CAgentsRegain", Integer.toString(breedCAgentsRegained.size()));
		System.out.println(result);
		
		return result;
	}

}
