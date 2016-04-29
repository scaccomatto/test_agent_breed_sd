package com.simudynetest.agentbreed;

import java.util.List;
import java.util.Map;

import com.simudynetest.model.Agent;


public interface AgentService {
	
	public Map <String, String> calculateAgentsBreed(List<Agent> agents, double brandFactor, int years);
	
	public Map <String, String> calculateAgentsBreedFunctional(List<Agent> agents, double brandFactor, int years);

}
