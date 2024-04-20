// RM97187 Victor Shimada Serete

package br.com.fiap.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.mp.dto.RequisicaoNovoPedido;
import br.com.fiap.mp.model.Pedido;
import br.com.fiap.mp.repository.PedidoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/formulario")
	public ModelAndView formulario(RequisicaoNovoPedido requisicao) {
		ModelAndView mv = new ModelAndView("pedido/formulario");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
		ModelAndView mv;
		if (result.hasErrors()) {
			mv = new ModelAndView("pedido/formulario");
			return mv;
		}		
		Pedido pedido = new Pedido(requisicao);
		pedidoRepository.save(pedido);
		mv = new ModelAndView("redirect:/home");
		return mv;
	}
	
}





