package com.rudrasiva.droolsdemo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.api.definition.KieDefinition.KnowledgeType;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rudrasiva.droolsdemo.model.BusinessRule;
import com.rudrasiva.droolsdemo.model.Product;

@Service
public class ProductService {

	@Autowired
	private KieContainer kieContainer;

	public Product getProductDiscount(Product product) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(product);
		kieSession.fireAllRules();
		kieSession.dispose();
		return product;
	}

	public List<BusinessRule> getRules() {
		List<BusinessRule> bRules = new ArrayList<>();
		KiePackage kiePackage = kieContainer.getKieBase("rules").getKiePackage("rules");
		Collection<FactType> factTypes = kiePackage.getFactTypes();
		for(FactType factType: factTypes) {
			System.out.println("Fact name: " + factType.getSimpleName());
		}
		Collection<Rule> rules = kiePackage.getRules();
		System.out.println(rules);
		for (Rule rule : rules) {
			BusinessRule br = new BusinessRule();
			br.setRuleName(rule.getName());
			bRules.add(br);
		}
		return bRules;
	}

}
