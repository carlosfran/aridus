package br.uern.aridus.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.openrdf.repository.RepositoryException;

import br.uern.aridus.model.QueryRewritterIface;
import br.uern.aridus.ontocoord.AlignmentManager;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.SortCondition;
import com.hp.hpl.jena.shared.PrefixMapping;
import com.hp.hpl.jena.shared.impl.PrefixMappingImpl;
import com.hp.hpl.jena.sparql.algebra.AlgebraGenerator;
import com.hp.hpl.jena.sparql.algebra.Op;
import com.hp.hpl.jena.sparql.algebra.OpAsQuery;
import com.hp.hpl.jena.sparql.algebra.op.OpUnion;
import com.hp.hpl.jena.sparql.core.Prologue;

@WebService(targetNamespace = "http://ws.aridus.uern.br/", 
portName = "QueryRewritterPort", serviceName = "QueryRewritterService",
endpointInterface = "br.uern.aridus.model.QueryRewritterIface")
public class QueryRewritter implements QueryRewritterIface {
	AlgebraGenerator algebra;
	AlignmentManager alignProcess;
	OpTransformer transf;

	public QueryRewritter() throws RepositoryException {
		algebra = new AlgebraGenerator();
		alignProcess = new AlignmentManager();
		transf = new OpTransformer();
	}

	// 1
	@Override
	public String queryRewritter1(String query) {
		Query qs = QueryFactory.create(query);
		Query qr = queryRewritter(qs);
		return qr.toString();
	}

	public Query queryRewritter(Query query) {
		return opRewritter(query);
	}

	// 2
	@Override
	public String queryRewritter2(String query, String[][] alignEntities) {
		Query query1 = QueryFactory.create(query);
		PrefixMappingImpl prefixMap = new PrefixMappingImpl();
		prefixMap.setNsPrefixes(query1.getPrefixMapping());

		List<Op> querys = new ArrayList<Op>();
		Op op = algebra.compile(query1);
		// System.out.println(op.toString());
		querys.add(op);

		transf.setMapping(alignEntities);
		Op op1 = transf.transform(op);

		if (op1 != null && !querys.contains(op1)) {
			querys.add(op1);
		}

		Query q = unionQuery(querys);
		q.setPrefixMapping(prefixMap);
		return q.toString();
	}

	// 3
	@Override
	public String queryRewritter3(String query, String[] alignIDList) {

		int count_vocab = 0;
		Query query1 = QueryFactory.create(query);
		PrefixMappingImpl prefixMap = new PrefixMappingImpl();
		prefixMap.setNsPrefixes(query1.getPrefixMapping());

		List<Op> querys = new ArrayList<Op>();
		Op op = algebra.compile(query1);
		// System.out.println(op.toString());
		querys.add(op);

		List<String> aligns = Arrays.asList(alignIDList);
		Iterator<String> iAligns = aligns.iterator();
		while (iAligns.hasNext()) {
			String alignid = iAligns.next();
			// System.out.println(alignid);
			String[][] mapA = alignProcess.getMap(alignid);
			transf.setMapping(mapA);
			Op op1 = transf.transform(op);

			if (op1 != null && !querys.contains(op1)) {
				querys.add(op1);
				// No lugar do count_vocab, inserir um novo mecanismo para
				// pegar prefix muito comuns
				String voc = alignProcess.getVocabTarget(alignid);
				prefixMap.setNsPrefix("vocab" + count_vocab, voc);
				count_vocab++;
			}
		}

		Query q = unionQuery(querys);
		q.setPrefixMapping(prefixMap);
		return q.toString();
	}

	// 4
	@Override
	public String queryRewritter4(String query, String alignID) {

		int count_vocab = 0;
		Query query1 = QueryFactory.create(query);
		PrefixMappingImpl prefixMap = new PrefixMappingImpl();
		prefixMap.setNsPrefixes(query1.getPrefixMapping());

		List<Op> querys = new ArrayList<Op>();
		Op op = algebra.compile(query1);
		// System.out.println(op.toString());
		querys.add(op);

		String[][] mapA = alignProcess.getMap(alignID);
		transf.setMapping(mapA);
		Op op1 = transf.transform(op);

		if (op1 != null && !querys.contains(op1)) {
			querys.add(op1);
			// No lugar do count_vocab, inserir um novo mecanismo para
			// pegar prefix muito comuns
			String voc = alignProcess.getVocabTarget(alignID);
			prefixMap.setNsPrefix("vocab" + count_vocab, voc);
			count_vocab++;
		}

		Query q = unionQuery(querys);
		q.setPrefixMapping(prefixMap);
		return q.toString();
	}

	public Query opRewritter(Query query) {

		int count_vocab = 0;
		PrefixMappingImpl prefixMap = new PrefixMappingImpl();
		prefixMap.setNsPrefixes(query.getPrefixMapping());

		List<Op> querys = new ArrayList<Op>();
		Op op = algebra.compile(query);
		// System.out.println(op.toString());
		querys.add(op);

		// Para cada vocabul�rio usado na consulta
		List<String> vocabs = this.listVocabs(query);
		Iterator<String> i = vocabs.iterator();
		String vocab = "";
		while (i.hasNext()) {
			vocab = (String) i.next();
			// System.out.println(vocab);
			/*
			 * new Thread(){ public void run(){
			 * System.out.println("thread "+this.getId()); } }.start();
			 */

			// Obt�m alinhamentos do vocabul�rio
			List<String> aligns = Arrays.asList(alignProcess
					.getAlignments(vocab));
			// String[] aligns = alignProcess.getAlignments(vocab);

			Iterator<String> iAligns = aligns.iterator();
			while (iAligns.hasNext()) {

				String alignid = iAligns.next();
				// System.out.println(alignid);
				String[][] mapA = alignProcess.getMap(alignid);
				transf.setMapping(mapA);
				Op op1 = transf.transform(op);

				// N�o funciona... realiza v�rias vezes...
				// Por�m, � como usam...
				// Op op1 = Transformer.transform(transf, op);
				// Query q1 = OpAsQuery.asQuery(op1);

				if (op1 != null && !querys.contains(op1)) {
					querys.add(op1);
					// No lugar do count_vocab, inserir um novo mecanismo para
					// pegar prefix muito comuns
					String voc = alignProcess.getVocabTarget(alignid);
					prefixMap.setNsPrefix("vocab" + count_vocab, voc);
					count_vocab++;
				}
			}
		}

		Query q = unionQuery(querys);
		q.setPrefixMapping(prefixMap);
		return q;
	}

	public Query unionQuery(List<Op> ops) {

		Query query = null;

		Op op1 = null, op2;
		if (ops.size() >= 1) {
			Iterator<Op> iq = ops.iterator();

			if (ops.size() == 1) {
				op1 = iq.next();
			} else {
				op1 = iq.next();
				while (iq.hasNext()) {
					op2 = iq.next();
					op1 = new OpUnion(op1, op2);
				}
			}
			// Otimiza��o - Opcional
			// op1 = Algebra.optimize(op1);
			query = OpAsQuery.asQuery(op1);

			if (query.hasOrderBy()) {
				List<SortCondition> cond = query.getOrderBy();
				int s = cond.size();
				for (int i = 0; i < s; i++) {
					SortCondition sortCondition = cond.remove(cond.size() - 1);
					s--;
					if (!cond.contains(sortCondition)) {
						cond.add(sortCondition);
						s++;
					}
				}
			}
		}
		return query;
	}

	/**
	 * Obt�m uma lista dos vocabul�rios usados na consulta.
	 * 
	 * @param query
	 *            consulta SPARQL
	 * @return List<String> lista de vocabul�rios sem duplica��es
	 */
	public List<String> listVocabs(Query query) {
		
		PrefixMapping map = query.getPrefixMapping();
		Map<String, String> m = map.getNsPrefixMap();

		// Remove duplicados
		List<String> list = new ArrayList<String>();
		List<String> vocabs = new ArrayList<String>(m.values());
		Iterator<String> i = vocabs.iterator();
		String uri = "";
		while (i.hasNext()) {
			uri = (String) i.next();
			if (!list.contains(uri))
				list.add(uri);
		}
		return list;
	}
	
	public String[] listVocabsInQuery(String squery){
		List<String> s = listVocabs(squery);
		String[] list = new String[s.size()];
		return list;
	}
	
	@Override
	public List<String> listVocabs(String squery) {
		Query query = QueryFactory.create(squery);
		Prologue p = (Prologue) query;
		PrefixMapping map = p.getPrefixMapping();
		Map<String, String> m = map.getNsPrefixMap();

		// Remove duplicados
		List<String> list = new ArrayList<String>();
		List<String> vocabs = new ArrayList<String>(m.values());
		Iterator<String> i = vocabs.iterator();
		String uri = "";
		while (i.hasNext()) {
			uri = (String) i.next();
			if (!list.contains(uri)) {
				list.add(uri);
			}
			// System.out.println(uri);
		}
		return list;
	}
}
