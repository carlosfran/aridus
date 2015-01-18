package br.uern.aridus.ws;

import javax.jws.WebService;
import javax.ws.rs.Path;

import org.openrdf.repository.RepositoryException;

import br.uern.aridus.model.AlignmentImportIface;
import br.uern.aridus.ontocoord.AlignmentManager;

@WebService(targetNamespace = "http://ws.aridus.uern.br/", portName = "AlignmentImportPort", serviceName = "AlignmentImportService", endpointInterface = "br.uern.aridus.model.AlignmentImportIface")
@Path("/align")
public class AlignmentImport implements AlignmentImportIface {

	AlignmentManager alignProcess;

	public AlignmentImport() throws RepositoryException {
		alignProcess = new AlignmentManager();
	}

	@Override
	public String alignImport(String urlIDAlign) {
		String ret = alignProcess.alignImport(urlIDAlign);
		return ret;
	}
}
