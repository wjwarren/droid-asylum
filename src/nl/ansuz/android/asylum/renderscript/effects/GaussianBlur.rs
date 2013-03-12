#pragma version(1)
#pragma rs java_package_name(nl.ansuz.android.asylum.renderscript.effects)

rs_allocation gIn;
rs_allocation gOut;
rs_script gScript;

// Linear set
//float3 mOffset = {0.0f, 1.3846153846f, 3.2307692308f};
//float3 mWeight = {0.2270270270f, 0.3162162162f, 0.0702702703f};

// Discrete set
float mOffset[5] = {0.0f, 1.0f, 2.0f, 3.0f, 4.0f};
float mWeight[5] = {0.2270270270f, 0.1945945946f, 0.1216216216f, 0.0540540541f, 0.0162162162f};

// Number of passes to apply the blur effect for.
int passes = 1;

// The scale factor for the sampling offsets.
int factor = 1;

uint32_t height;
uint32_t width;

/**
 * Applies Gaussian blur, based on the discrete algorithm in the link below.
 * 
 * TODO: Optimize! 
 *		- No need for 2 for loops. 
 * 		- Better variable reuse (create outside for loop). 
 *		- Apply colour_multipier only at the end.
 * TODO: Check that composite colours don't over/undershoot, i.e. are in the range 0-1?
 *
 * @see: http://rastergrid.com/blog/2010/09/efficient-gaussian-blur-with-linear-sampling/
 * @see http://developer.android.com/reference/renderscript/globals.html 
 *
 * @param *v_in Original (input) pixel.
 * @param *v_out Modified (output) pixel.
 * @param *usrData ???
 * @param x Pixel X position.
 * @param y Pixel Y position.
 */
void root(const uchar4 *v_in, uchar4 *v_out, const void *usrData, uint32_t x, uint32_t y) {
	int blurSteps = 5;
	float colour_multipier = 0.45f;
	float3 pixel_colour = rsUnpackColor8888(*v_in).rgb * mWeight[0];
	
	// Horizontal blur
	for (int i = 1; i < blurSteps; i++) {
		// Forward
		if(x + mOffset[i] * factor < width) {
			const uchar4 *neighbourHorizontalForward = rsGetElementAt(gIn, x + mOffset[i] * factor, y);
			pixel_colour += rsUnpackColor8888(*neighbourHorizontalForward).rgb * mWeight[i] * colour_multipier;
		}
		// Backward
		if(x - mOffset[i] * factor >= 0) {
			const uchar4 *neighbourHorizontalBackward = rsGetElementAt(gIn, x - mOffset[i] * factor, y);
			pixel_colour +=  rsUnpackColor8888(*neighbourHorizontalBackward).rgb * mWeight[i] * colour_multipier;
		}
	}
	
	// Vertical blur
	for (int k = 1; k < blurSteps; k++) {
		// UP
		if(y - mOffset[k] * factor >= 0) {
			const uchar4 *neighbourVerticalUp = rsGetElementAt(gIn, x, y - mOffset[k] * factor);
			pixel_colour += rsUnpackColor8888(*neighbourVerticalUp).rgb * mWeight[k] * colour_multipier;
		}
		
		// Down
		if(y + mOffset[k] * factor < height) {
			const uchar4 *neighbourVerticalDown = rsGetElementAt(gIn, x, y + mOffset[k] * factor);
			pixel_colour += rsUnpackColor8888(*neighbourVerticalDown).rgb * mWeight[k] * colour_multipier;
		}
	}
	
	// Create output
	*v_out = rsPackColorTo8888(pixel_colour);
}

void filter() {
	height = rsAllocationGetDimY(gIn);
	width = rsAllocationGetDimX(gIn);
	
	rsForEach(gScript, gIn, gOut, NULL);
	for(int i = 0; i < passes-1; i++) {
		rsSetObject(&gIn, gOut);
		rsForEach(gScript, gIn, gOut, NULL);
	}
}