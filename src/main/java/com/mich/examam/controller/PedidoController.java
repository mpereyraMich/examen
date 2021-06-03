package com.mich.examam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mich.examam.model.Pedido;
import com.mich.examam.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired private PedidoService pedidoService;
	
	@PostMapping("/save")
	public ResponseEntity<Object>  save(@RequestBody Pedido pedido) {
		try {	
			pedidoService.save(pedido);
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() {
		try {	
			List<Pedido> list = pedidoService.getAll();
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
