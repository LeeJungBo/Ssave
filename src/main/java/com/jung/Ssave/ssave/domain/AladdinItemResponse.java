package com.jung.Ssave.ssave.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AladdinItemResponse {

	private List<Item> item; // 반드시 변수이름이 갖다쓸려는 사이트의 데이터의 키값과 같아야한다.
	
	
	
}
