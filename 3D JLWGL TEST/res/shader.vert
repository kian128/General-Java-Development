varying vec3 color;

void main() {
	color = gl_color.rgb;
	gl_Position = ftransform();
}