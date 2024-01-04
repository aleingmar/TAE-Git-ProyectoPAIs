
package acme.features.medico.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;

@Service
public class TipoMedicoService extends AbstractService<Medico, Medico> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoMedicoRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Medico medico;
		Principal principal;

		principal = super.getRequest().getPrincipal();
		medico = this.repository.sacaMedicoLogueado(principal.getActiveRoleId());

		super.getBuffer().setData(medico);
	}

	@Override
	public void unbind(final Medico object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "especialidad", "tipoMedico", "userAccount.username", "userAccount.identity.email");

		super.getResponse().setData(tuple);
	}

	public Medico getCurrentMedico() {
		final Principal principal = super.getRequest().getPrincipal();
		final Medico medico = this.repository.sacaMedicoLogueado(principal.getActiveRoleId());
		return medico;
	}

}
