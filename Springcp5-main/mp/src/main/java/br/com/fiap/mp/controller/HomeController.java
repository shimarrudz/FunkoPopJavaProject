// RM97187 Victor Shimada Serete

package br.com.fiap.mp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.mp.model.Pedido;
import br.com.fiap.mp.model.StatusPedido;
import br.com.fiap.mp.repository.PedidoRepository;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	public ModelAndView home() {				
		List<Pedido> pedidos = pedidoRepository.findAll();
		ModelAndView mv = new ModelAndView("home");		
		mv.addObject("pedidos", pedidos);
		return mv;
	}
	
	@GetMapping("/{status}")
	public ModelAndView porStatus(@PathVariable("status") String status) {
		List<Pedido> pedidos = pedidoRepository.findByStatus(
				StatusPedido.valueOf(status.toUpperCase()));
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("pedidos", pedidos);
		mv.addObject("status", status);
		return mv;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView onError() {
		ModelAndView mv = new ModelAndView("redirect:/home");
		return mv;
	}
	
}









