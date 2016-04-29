package com.simudyne.test.agenttest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.simudynetest.agentbreed.AgentService;
import com.simudynetest.agentbreed.AgentServiceImpl;
import com.simudynetest.model.Agent;
import com.simudynetest.model.AgentBreed;

public class AgentServiceTest {
	
	List<Agent> agents;
	double brandFactor=2.2;
	
	@Before
	public void setUp(){
		
		Agent a1=new Agent(AgentBreed.Breed_C, 132802001, 66, 3, 250.0, 25.3, 16.6,	5.1, true, 4);
		Agent a2=new Agent(AgentBreed.Breed_NC, 100831003, 43, 4,2250.0, 7.4, 81.1,	2.5, true, 2);
		Agent a3=new Agent(AgentBreed.Breed_C, 132802008, 66, 3, 250.0, 25.3, 16.6,	5.1, true, 3);
		agents = new ArrayList<Agent>();
		agents.add(a1);
		agents.add(a2);
		agents.add(a3);
		
	}
	
	@Test
	public void testCalculateAgentsBreed(){
		AgentService agentSer= new AgentServiceImpl();
		agentSer.calculateAgentsBreedFunctional(agents, brandFactor,15);
		agentSer.calculateAgentsBreed(agents, brandFactor,15);
		
	}

}
