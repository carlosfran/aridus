package br.uern.aridus.query;

import java.util.Collection;

import org.openrdf.query.Query;

import br.uern.aridus.ontocoord.AlignmentManager;

public class QueryTransformer implements Runnable {
	Collection<Query> queryCollect;
	AlignmentManager alignProcess;
	
	public QueryTransformer(Collection<Query> queryCollect, AlignmentManager alignProcess){
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
