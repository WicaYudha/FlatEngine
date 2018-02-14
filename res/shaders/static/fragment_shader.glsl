#version 400 core

in vec3 pass_normal;
in vec2 pass_textureCoordinates;

out vec4 out_colour;

conts vec3 lightDirection = normalize(vec3(0.2, -1.0, 0.3));
conts float ambient = 0.3;

void main(void){
	float brightness = max(dot(-lightDirection, normalize(pass_normal)), 0.0) + ambient;
}